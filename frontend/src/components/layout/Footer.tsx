const currentYear: number = new Date().getFullYear();

const Footer = () => { 
    return ( 
        <>
            <footer className="bg-re-dark-blue">
                <div className="text-gray-400 text-center py-4" >
                    Copyright © {currentYear} by Papapanagiotou Real Estate - Powered by <span className="text-white">Papapanagiotou Panagiotis</span>. All rights reserved.
                </div>
            </footer>
        </>
    );

}

export default Footer;