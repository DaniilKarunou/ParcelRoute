document.addEventListener("DOMContentLoaded", function() {
    const createParcelForm = document.getElementById("createParcelForm");
    const parcelTableBody = document.getElementById("parcelTableBody");

    // Funkcja do pobierania listy paczek
    async function fetchParcels() {
        try {
            const response = await fetch("/api/parcels/");
            return await response.json();
        } catch (error) {
            console.error("Error fetching parcels:", error);
            return [];
        }
    }

    // Funkcja do aktualizacji tabeli z paczkami
    async function updateParcelTable() {
        const parcels = await fetchParcels();

        parcelTableBody.innerHTML = "";
        parcels.forEach(parcel => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${parcel.id}</td>
                <td>${parcel.size}</td>
                <td>${parcel.parcelType}</td>
                <td>${parcel.sender.name}</td>
                <td>${parcel.recipient.name}</td>
                <td>
                    <button class="pickupButton">Pick up</button>
                </td>
            `;
            parcelTableBody.appendChild(row);
        });
    }

    createParcelForm.addEventListener("submit", async function(event) {
        event.preventDefault();

        // Pobierz dane z formularza
        const formData = new FormData(createParcelForm);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        // Wyślij dane do API (tu musisz dodać kod do wysłania danych do swojego API)

        // Zaktualizuj tabelę z paczkami
        updateParcelTable();

        // Wyczyść formularz
        createParcelForm.reset();
    });

    // Obsługa przycisku do odbioru
    parcelTableBody.addEventListener("click", async function(event) {
        if (event.target.classList.contains("pickupButton")) {
            const row = event.target.closest("tr");
            const parcelId = row.querySelector("td").textContent;

            // Tu musisz dodać kod do obsługi odbioru paczki (wysłanie zapytania do API)

            // Zaktualizuj tabelę z paczkami
            updateParcelTable();
        }
    });

    // Inicjalizacja: Załaduj istniejące paczki do tabeli
    updateParcelTable();
});
