import { BrowserRouter, Route, Routes } from 'react-router'
import HomePage from './pages/HomePage'
import NotFoundPage from './pages/NotFoundPage'
import TermsOfUseEn from './pages/en/TermsOfUse'
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
            <Route path="form-assign" element={<MainLayout><AssignForm/></MainLayout>} />
            <Route path="form-request" element={<MainLayout><RequestForm/></MainLayout>}/>
            <Route path='terms' element={<MainLayout><TermsOfUseEn/></MainLayout>} />
            <Route path="about" element={<MainLayout><AboutUsEn/></MainLayout>} />
            <Route path="services" element={<MainLayout><ServicesEn/></MainLayout>} />
            <Route path='login' element={<LoginEn/>} />
            <Route path='dashboard' element={<MainLayout><DashboardPage/></MainLayout>} />
            <Route path='new-password-en' element={<NewPasswordEn/>} />
            <Route path='*' element={<MainLayout><NotFoundPage/></MainLayout>} />
          </Routes>
      </BrowserRouter>
        
    </>
  )
}

export default App
