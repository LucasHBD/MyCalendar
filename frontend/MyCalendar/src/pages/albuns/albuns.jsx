import "./albuns.css";
import { Link } from "react-router-dom";
import imgbg from "../../assets/falling-leaves.png"

function Albuns() {

    const [albuns, setAlbuns] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8081/api/albuns")
            .then(res => res.json())
            .then(data => setAlbuns(data))
            .catch(err => console.error("Error fetching albuns:", err));
    }, []);

    return (
        <>
            <header>
                <div>
                    <h1>MyCalendar</h1>
                </div>
                <nav>
                    <Link to="/albuns">Albuns</Link>
                    <Link to="/configuracoes">LucasHBD</Link>
                </nav>
            </header>
            <main>
                {albuns.length === 0 ? (
                    <>
                        <div className="null">
                            <img src={imgbg} alt="Falling Leaves"></img>
                        </div>
                        <div className="nulltext">
                            <p>Você não possui albuns ainda</p>
                        </div>
                    </>
                ) : (
                    <div className="grid">
                        {albuns.map(album => (
                            <div key={album.id} className="card">
                                <h3>{album.nome}</h3>
                                {album.fotos.length > 0 && (
                                    <img src={album.fotos[0].url} alt=""></img>
                                )}
                            </div>
                        ))}
                    </div>
                )}
            </main>
        </>
    );
}

export default Albuns