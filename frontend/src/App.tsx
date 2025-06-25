import { BrowserRouter, Route, Routes } from 'react-router'
import HomePage from './pages/HomePage'
import NotFoundPage from './pages/NotFoundPage'
import Footer from './components/layout/Footer'


function App() {
  

  return (
    <>
      <BrowserRouter>
          <Routes>
            <Route index element={<HomePage />} />
            <Route path='*' element={<NotFoundPage/>} />
            <Route path='footer' element={<Footer/>} />
          </Routes>
      </BrowserRouter>
        
    </>
  )
}

export default App
