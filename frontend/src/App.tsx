import { BrowserRouter, Route, Routes } from 'react-router'
import HomePage from './pages/HomePage'


function App() {
  

  return (
    <>
      <BrowserRouter>
          <Routes>
            <Route index element={<HomePage />} />
          </Routes>
      </BrowserRouter>
        
    </>
  )
}

export default App
