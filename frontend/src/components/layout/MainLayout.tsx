import HeaderEn from './HeaderEn';
import Footer from './Footer';
import type { ReactNode } from 'react';


const MainLayout = ({children}: {children: ReactNode}) => {
  return (
        <>
            <div className="flex flex-col min-h-screen">
                <HeaderEn />
                <main className="flex-grow container mx-auto px-4 py-8">
                    {children}
                </main>
                <Footer />
            </div>  
        </>    
    );
};

export default MainLayout;