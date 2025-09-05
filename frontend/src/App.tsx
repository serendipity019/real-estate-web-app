import { BrowserRouter, Route, Routes } from 'react-router'
import HomePage from './pages/HomePage'
import NotFoundPage from './pages/NotFoundPage'
import Footer from './components/layout/Footer'
import TermsOfUseEn from './pages/en/TermsOfUse'
import ContactInfoEl from './components/layout/ContactInfoEl'
import AboutUsEn from './pages/en/AboutUs'
import ServicesEn from './pages/en/Services'
import AssignForm from './components/forms/AssignForm'
import RequestForm from './components/forms/RequestForm'
import LoginEn from './pages/en/LoginEn'
import DashboardPage from './pages/en/DashboardEnPage'
import MainLayout from './components/layout/MainLayout'
import NewPasswordEn from './pages/en/ForgotPassword'


function App() {
  

  return (
    <>
      <BrowserRouter>
          <Routes>
            <Route index element={<HomePage />} />
            <Route path="form-assign" element={<AssignForm/>} />
            <Route path="form-request" element={<RequestForm/>}/>
            <Route path='footer' element={<Footer/>} />
            <Route path='terms' element={<TermsOfUseEn/>} />
            <Route path="contact" element={<ContactInfoEl/>} />
            <Route path="about" element={<AboutUsEn/>} />
            <Route path="services" element={<ServicesEn/>} />
            <Route path='login' element={<LoginEn/>} />
            <Route path='dashboard' element={<MainLayout><DashboardPage/></MainLayout>} />
            <Route path='new-password-en' element={<NewPasswordEn/>} />
            <Route path='*' element={<NotFoundPage/>} />
          </Routes>
      </BrowserRouter>
        
    </>
  )
}

export default App
