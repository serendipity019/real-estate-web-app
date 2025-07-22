import { BrowserRouter, Route, Routes } from 'react-router'
import HomePage from './pages/HomePage'
import NotFoundPage from './pages/NotFoundPage'
import Footer from './components/layout/Footer'
import TermsOfUseEn from './pages/en/TermsOfUse'
import ContactInfoEl from './components/layout/ContactInfoEl'
import AboutUsEn from './pages/en/AboutUs'
import ServicesEn from './pages/en/Services'
import CustomerContactInfo from './components/forms/CustomerContactForm'

function App() {
  

  return (
    <>
      <BrowserRouter>
          <Routes>
            <Route index element={<HomePage />} />
            <Route path="form" element={<CustomerContactInfo/>} />
            <Route path='footer' element={<Footer/>} />
            <Route path='terms' element={<TermsOfUseEn/>} />
            <Route path="contact" element={<ContactInfoEl/>} />
            <Route path="about" element={<AboutUsEn/>} />
            <Route path="services" element={<ServicesEn/>} />
            <Route path='*' element={<NotFoundPage/>} />
          </Routes>
      </BrowserRouter>
        
    </>
  )
}

export default App
