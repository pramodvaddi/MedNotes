const API = "https://mednotes-3.onrender.com/api/diseases";


// SAVE / UPDATE
document.getElementById("diseaseForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const id = document.getElementById("diseaseId").value;

  const body = {
    diseaseName: diseaseName.value,
    description: description.value,
    medications: medications.value
  };

  const url = id ? `${API}/${id}` : API;
  const method = id ? "PUT" : "POST";

  const res = await fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  });

  if (res.ok) {
    diseaseForm.reset();
    diseaseId.value = "";
    successMsg.innerText = "Saved successfully";
    loadAll();
  }
});

// 🔍 SEARCH BY NAME
async function searchByName() {
  const name = searchInput.value.trim();
  if (!name) return;

  const res = await fetch(`${API}/search?name=${encodeURIComponent(name)}`);
  const data = await res.json();
  renderCards(data);
}

// 📄 VIEW ALL
async function loadAll() {
  const res = await fetch(`${API}?page=0&size=50`);
  const data = await res.json();
  renderCards(data.content);
}

// 🎨 RENDER CARDS (THIS WAS MISSING)
function renderCards(list) {
  const cards = document.getElementById("cards");
  cards.innerHTML = "";

  if (!list || list.length === 0) {
    cards.innerHTML = "<p>No records found</p>";
    return;
  }

  list.forEach(d => {
    cards.innerHTML += `
      <div class="card">
        <h3>${d.diseaseName}</h3>

        <div class="details">
          <p>${d.description}</p>

          <strong>Medications:</strong>
          <ul>
            ${d.medications.split(",").map(m => `<li>${m.trim()}</li>`).join("")}
          </ul>

          <div class="card-actions">
            <button class="update-btn"
              onclick="prefillForUpdate(${d.id}, '${d.diseaseName}', '${d.description}', '${d.medications}')">
              Edit
            </button>
            <button class="delete-btn"
              onclick="deleteDisease(${d.id})">
              Delete
            </button>
          </div>
        </div>
      </div>
    `;
  });
}

// ✏️ PREFILL UPDATE
function prefillForUpdate(id, name, desc, meds) {
  diseaseId.value = id;
  diseaseName.value = name;
  description.value = desc;
  medications.value = meds;
  successMsg.innerText = "Editing… click Save to update";
}

// 🗑 DELETE
async function deleteDisease(id) {
  await fetch(`${API}/${id}`, { method: "DELETE" });
  loadAll();
}
