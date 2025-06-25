import { BrowserRouter, Route, Routes } from 'react-router'
import HomePage from './pages/HomePage'
import NotFoundPage from './pages/NotFoundPage'


function App() {
  

  return (
    <>
      <BrowserRouter>
          <Routes>
            <Route index element={<HomePage />} />
            <Route path='*' element={<NotFoundPage/>} />
          </Routes>
      </BrowserRouter>
        
    </>
  )
}

export default App
