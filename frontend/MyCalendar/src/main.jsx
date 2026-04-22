import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Home from './pages/home'
import Cadastro from './pages/cadastro/cadastro'
import Albuns from './pages/albuns/albuns'
import {createBrowserRouter, RouterProvider} from "react-router-dom"


const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/cadastro",
    element: <Cadastro />
  },
  {
    path: "/albuns",
    element: <Albuns />
  }
]);

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
