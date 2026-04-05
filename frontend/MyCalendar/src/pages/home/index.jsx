import './style.css'
import img1 from "../../assets/Amor2.jpeg"
import img2 from "../../assets/WhatsApp Image 2026-04-05 at 11.24.46.jpeg"
import username from "../../assets/person.png"
import password from "../../assets/locked-computer.png"
import { Link } from "react-router-dom"

function Home() {

  return (
    <div className='container'>
      <div className='left-box'>
        <div className='photos'>
            <img src={img1} alt="img1" />
            <img src={img2} alt="img2" />
        </div>
      </div>
      <div className='right-box'>
        <form>
          <h1>MyCalendar</h1>
          <div className='input-group'>
            <img src={username} className='icon'></img>
            <input name='username' type='text' placeholder='username'></input>
          </div>
          <div className='input-group'>
            <img src={password} className='icon'></img>
            <input name='password' type='password' placeholder='password'></input>
          </div>
          <div className='checkbox-group'>
            <input name='checkbox' type='checkbox'></input>
            <label htmlFor='Lembrar'> Lembrar de mim</label>
          </div>
          <div>
            <Link to='/cadastro'>Não possui conta? Cadastre-se</Link>
          </div>
          <button type='button'>Login</button>
        </form>
      </div>
    </div>
  )
}

export default Home
