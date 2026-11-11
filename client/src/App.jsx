import { useCallback, useEffect, useState } from "react";

const API_URL = `http://${window.location.hostname || "localhost"}:8089/people`;

export default function App() {
  const [name, setName] = useState("");
  const [people, setPeople] = useState([]);
  const [message, setMessage] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const [isSaving, setIsSaving] = useState(false);

  const loadPeople = useCallback(async () => {
    setIsLoading(true);

    try {
      const response = await fetch(API_URL);
      const result = await response.json();

      if (!response.ok || result.error) {
        setMessage(result.message || "No se pudieron cargar las personas");
        setPeople([]);
        return;
      }

      setMessage("");
      setPeople(Array.isArray(result.data) ? result.data : []);
    } catch {
      setMessage("No se pudo conectar con el backend");
      setPeople([]);
    } finally {
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    const timeoutId = window.setTimeout(() => {
      loadPeople();
    }, 0);

    return () => window.clearTimeout(timeoutId);
  }, [loadPeople]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const trimmedName = name.trim();
    if (!trimmedName) {
      setMessage("El nombre es obligatorio");
      return;
    }

    setIsSaving(true);
    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: trimmedName }),
      });
      const result = await response.json();

      if (!response.ok || result.error) {
        throw new Error(result.message || "No se pudo guardar la persona");
      }

      setName("");
      await loadPeople();
    } catch (error) {
      setMessage(error.message || "No se pudo guardar la persona");
    } finally {
      setIsSaving(false);
    }
  };

  return (
    <main className="container py-4">
      <h1 className="mb-4">Personas</h1>

      <form className="row g-3 mb-3" onSubmit={handleSubmit}>
        <div className="col-12 col-md-8">
          <label htmlFor="name" className="form-label">Nombre</label>
          <input
            id="name"
            name="name"
            type="text"
            className="form-control"
            placeholder="Nombre"
            value={name}
            onChange={(event) => setName(event.target.value)}
            disabled={isSaving}
            required
          />
        </div>
        <div className="col-12 col-md-4 d-flex align-items-end">
          <button type="submit" className="btn btn-primary" disabled={isSaving}>
            {isSaving ? "Guardando..." : "Enviar"}
          </button>
        </div>
      </form>

      {message && <p className="alert alert-info">{message}</p>}

      <table className="table table-striped">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
          </tr>
        </thead>
        <tbody>
          {isLoading ? (
            <tr>
              <td colSpan="2">Cargando...</td>
            </tr>
          ) : people.length === 0 ? (
            <tr>
              <td colSpan="2">No hay personas registradas</td>
            </tr>
          ) : (
            people.map((person) => (
              <tr key={person.id}>
                <td>{person.id}</td>
                <td>{person.name}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </main>
  );
}
