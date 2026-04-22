import './cadastro.css'
import img1 from "../../assets/Amor2.jpeg"
import img2 from "../../assets/WhatsApp Image 2026-04-05 at 11.24.46.jpeg"
import username from "../../assets/person.png"
import password from "../../assets/locked-computer.png"
import { Link } from "react-router-dom"
import { useState } from 'react'
import { useNavigate } from 'react-router-dom';

function Home() {
  const [formData, setFormData] = useState({
    login: '',
    password: '',
    role: 'USER'
  });

  const handleCadastro = () =>{
    fetch('http://localhost:8081/api/auth/register', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formData)
    })
    .then(res => {
      if(!res.ok) {
        throw new Error('Erro ao criar usuário');
      }
      return res;
    })
    .then(data => {
      console.log("Usuário criado:", data);
      alert("Usuário criado com sucesso!");
      navigate('/');
    })
    .catch(err => console.error("Erro:", err));
  };

  const navigate = useNavigate();


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
            <input name='login' type='text' placeholder='login' value={formData.login} onChange={(e) => setFormData({...formData, login: e.target.value})}></input>
          </div>
          <div className='input-group'>
            <img src={password} className='icon'></img>
            <input name='password' type='password' placeholder='password' value={formData.password} onChange={(e) => setFormData({...formData, password: e.target.value})}></input>
          </div>
          <div>
            <Link to='/'>Já possui conta? Entrar</Link>
          </div>
          <button type='button' onClick={handleCadastro}>Cadastrar</button>
        </form>
      </div>
    </div>
  )
}

export default Home
