// ─── Constants ───────────────────────────────────────────────────────────────

const SEARCH_ICON_SVG = `
  <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor"
       stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round">
    <circle cx="11" cy="11" r="8"/>
    <line x1="21" y1="21" x2="16.65" y2="16.65"/>
  </svg>
`;

// ─── DOM References ───────────────────────────────────────────────────────────

const srcInput      = document.getElementById('srcInput');
const dstInput      = document.getElementById('dstInput');
const searchBtn     = document.getElementById('searchBtn');
const swapBtn       = document.getElementById('swapBtn');
const resultsWrapper = document.getElementById('resultsWrapper');

// ─── Event Listeners ──────────────────────────────────────────────────────────

// Auto-uppercase + Enter to search
[srcInput, dstInput].forEach(input => {
  input.addEventListener('input', () => {
    const pos = input.selectionStart;
    input.value = input.value.toUpperCase();
    input.setSelectionRange(pos, pos);
  });
  input.addEventListener('keydown', e => {
    if (e.key === 'Enter') searchRoutes();
  });
});

searchBtn.addEventListener('click', searchRoutes);
swapBtn.addEventListener('click', swapCodes);

// ─── Helpers ─────────────────────────────────────────────────────────────────

/**
 * Swap the source and destination station codes.
 */
function swapCodes() {
  const temp = srcInput.value;
  srcInput.value = dstInput.value;
  dstInput.value = temp;
}

/**
 * Calculate human-readable duration between two HH:MM time strings.
 * Handles overnight routes (arrival < departure).
 */
function calcDuration(dep, arr) {
  try {
    const toMins = t => {
      const [h, m] = t.split(':').map(Number);
      return h * 60 + (m || 0);
    };
    let diff = toMins(arr) - toMins(dep);
    if (diff < 0) diff += 24 * 60;
    const h = Math.floor(diff / 60);
    const m = diff % 60;
    return `${h}h${m > 0 ? ' ' + m + 'm' : ''}`;
  } catch {
    return '—';
  }
}

/**
 * Format a 24h HH:MM time string to 12h H:MM format.
 */
function fmtTime(t) {
  if (!t) return '--:--';
  const [h, m] = t.split(':');
  const hh = parseInt(h, 10);
  const mm = m ? m.padStart(2, '0') : '00';
  const h12 = hh % 12 || 12;
  return `${h12}:${mm}`;
}

// ─── Rendering ────────────────────────────────────────────────────────────────

/**
 * Build HTML for a single train route card.
 */
function renderCard(route, idx) {
  const dur = calcDuration(route.departureTime, route.arrivalTime);
  return `
    <div class="train-card" style="animation-delay: ${idx * 0.06}s">
      <div class="card-top">
        <span class="train-id">ROUTE #${String(route.id).padStart(4, '0')}</span>
        <span class="duration-pill">⏱ ${dur}</span>
      </div>
      <div class="route">
        <div class="station">
          <div class="station-time">${fmtTime(route.departureTime)}</div>
          <div class="station-code">${route.source.stationcode}</div>
          <div class="station-name">${route.source.stationname}</div>
        </div>
        <div class="track-visual">
          <div class="track-train">🚄</div>
          <div class="track-line"></div>
          <div class="track-label">Direct</div>
        </div>
        <div class="station dest">
          <div class="station-time">${fmtTime(route.arrivalTime)}</div>
          <div class="station-code">${route.destination.stationcode}</div>
          <div class="station-name">${route.destination.stationname}</div>
        </div>
      </div>
    </div>
  `;
}

/**
 * Render an informational / error state box.
 */
function renderState({ icon, title, message, isError = false }) {
  return `
    <div class="state-box${isError ? ' error-box' : ''}">
      <div class="state-icon">${icon}</div>
      <div class="state-title">${title}</div>
      <div class="state-msg">${message}</div>
    </div>
  `;
}

// ─── Button State Helpers ─────────────────────────────────────────────────────

function setButtonLoading() {
  searchBtn.classList.add('loading');
  searchBtn.innerHTML = `<div class="spinner"></div> SEARCHING...`;
}

function resetButton() {
  searchBtn.classList.remove('loading');
  searchBtn.innerHTML = `${SEARCH_ICON_SVG} SEARCH TRAINS`;
}

// ─── Main Search Function ─────────────────────────────────────────────────────

async function searchRoutes() {
  const src = srcInput.value.trim();
  const dst = dstInput.value.trim();

  // Validate inputs
  if (!src || !dst) {
    resultsWrapper.innerHTML = renderState({
      icon: '⚠️',
      title: 'Missing Station Codes',
      message: 'Please enter both source and destination station codes to search.',
      isError: true,
    });
    return;
  }

  setButtonLoading();
  resultsWrapper.innerHTML = '';

  try {
    const url = `http://localhost:8086/search/byCode?sourceCode=${encodeURIComponent(src)}&destinationCode=${encodeURIComponent(dst)}`;
    const res = await fetch(url);

    if (!res.ok) throw new Error(`Server responded with status ${res.status}`);

    const data = await res.json();

    // No results
    if (!Array.isArray(data) || data.length === 0) {
      resultsWrapper.innerHTML = renderState({
        icon: '🔍',
        title: 'No Routes Found',
        message: `No train connections found between <strong>${src}</strong> and <strong>${dst}</strong>. Try different station codes.`,
      });
      return;
    }

    // Render results
    resultsWrapper.innerHTML = `
      <div class="results-header">
        <span class="results-title">// Available Routes</span>
        <span class="results-count">${data.length} FOUND</span>
      </div>
      ${data.map((route, i) => renderCard(route, i)).join('')}
    `;

  } catch (err) {
    let message = err.message;
    if (/fetch|network|failed/i.test(err.message)) {
      message = 'Could not connect to the API at <code>localhost:8086</code>. Make sure your backend server is running.';
    }
    resultsWrapper.innerHTML = renderState({
      icon: '🚨',
      title: 'Connection Error',
      message,
      isError: true,
    });
  } finally {
    resetButton();
  }
}
