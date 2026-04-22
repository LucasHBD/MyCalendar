import "./albuns.css";
import { Link } from "react-router-dom";
import imgbg from "../../assets/falling-leaves.png"
import { useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";

function Albuns() {

    const [albuns, setAlbuns] = useState([]);
    const [novoAlbum, setNovoAlbum] = useState("");
    const [menuOpen, setMenuOpen] = useState(false);
    const [usuario, setUsuario] = useState("");

    useEffect(() => {
        const token = localStorage.getItem("token");
        if(token){
            try {
                const decoded = jwtDecode(token);
                setUsuario(decoded.sub);
            } catch (error) {
                console.error("Error decoding token:", error);
            }
        }
        fetch("http://localhost:8081/api/albuns")
            .then(res => res.json())
            .then(data => setAlbuns(data))
            .catch(err => console.error("Error fetching albuns:", err));
    }, []);

    const criarAlbum = () => {
        if(!novoAlbum) return;

        fetch("http://localhost:8081/api/albuns", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({nome: novoAlbum})
        })
        .then(res => res.json())
        .then(() => {
            setNovoAlbum("");
            fetchAlbuns();
        })
        .catch(err => console.error(err))
    };

    const deletarAlbum = id => {
        fetch(`http://localhost:8081/api/albuns/${id}`, {
            method: "DELETE"
        })
        .then(() => fetchAlbuns())
        .catch(err => console.error(err));
    };

    const logout = () => {
        localStorage.removeItem("token");
        window.location.href = "/";
    }

    return (
        <>
            <header>
                <div>
                    <h1>MyCalendar</h1>
                </div>
                <nav>
                    <Link to="/albuns">Albuns</Link>
                    <div className="user-menu">
                        <span onClick={() => setMenuOpen(!menuOpen)}>
                            {usuario || "Usuário"}
                        </span>
                        {menuOpen && (
                            <div className="dropdown">
                                <button onClick={logout}>Sair</button>
                            </div>
                        )}
                    </div>
                </nav>
            </header>
            <main>
                <div className="create">
                    <input type="text" placeholder="Nome do álbum" value={novoAlbum} onChange={(e) =>setNovoAlbum(e.target.value)}></input>
                    <button onClick={criarAlbum}>Criar</button>
                </div>
                {albuns.length === 0 ? (
                    <div className="empty">
                        <img src={imgbg} alt="Falling Leaves" />
                        <p>Você não possui álbuns ainda</p>
                    </div>
                ) : (
                    <div className="grid">
                        {albuns.map(album => (
                            <div key={album.id} className="card">
                                <h3>{album.nome}</h3>

                                {/* imagem */}
                                {album.memorias?.length > 0 ? (
                                    <img
                                        src={album.memorias[0].caminhoImagem}
                                        alt=""
                                    />
                                ) : (
                                    <div className="no-image">Sem imagem</div>
                                )}

                                <button onClick={() => deletarAlbum(album.id)}>
                                    Deletar
                                </button>
                            </div>
                        ))}
                    </div>
                )}
            </main>
        </>
    );
}

export default Albuns