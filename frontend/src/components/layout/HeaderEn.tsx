import WhiteBgLogo from "../logo/WhiteBgLogo";
import {Phone, Smartphone, Mail, Facebook, Instagram} from 'lucide-react';
import {Link} from "react-router-dom";

const HeaderEn = () => {    
    return (
        <>
            <header className="h-[240px] bg-[#f6f6f6]">
                <div className="flex justify-between ">
                    <div className="items-start w-[320px] h-[200px]"><WhiteBgLogo/></div>
                    <div>
                        <div className="flex gap-2 mt-12">
                            <span className="flex mr-2 "><Phone className="text-black"/> 
                            <a href="tel:0302104578126" className="text-re-light hover:text-gray-800 ml-2"> (030) 2104578126</a>
                            </span>
                            <span className="flex"><Smartphone/>
                            <a href="tel:0306978706041" className="text-re-light hover:text-gray-800 ml-2"> (030) 6978706041</a>
                            </span>
                        </div>
                        <div className="my-8">
                            <span className="flex"><Mail/>
                            <a href= "email: serendipitya137@aueb.gr" className="text-re-light hover:text-gray-800 ml-2">serendipitya137@aueb.gr</a>
                            </span>
                        </div>
                    </div>
                    <div className="mt-12 mr-4">                        
                        <div className="flex gap-4 pl-16">
                            <span className="text-medium text-re-light font-semibold mt-6 -ml-10">Social media:</span>
                            <span className="-ml-4"> 
                            <a href= "https://www.facebook.com/" className="text-re-light hover:text-gray-800 ml-2"><Facebook/> </a>
                            </span>
                            <span> 
                                <a href= "https://www.instagram.com/" className="text-re-light hover:text-gray-800 ml-2"><Instagram/> </a>
                            </span>
                        </div>
                    </div>    
                </div>
                <nav className="bg-re-dark px-4 py-4 flex mt-3 justify-between items-center">
                    <div className="flex space-x-6">
                    <Link to="/" className="hover:text-re-light">Home</Link>
                    <Link to="/about" className="hover:text-re-light">About</Link>
                    <Link to="/services" className="hover:text-re-light">Services</Link>
                    <Link to="/form-assign" className="hover:text-re-light">Offer Property</Link>
                    <Link to="/form-request" className="hover:text-re-light">Request Property</Link>
                    </div>
                    <div>
                    <Link to="/login" className="bg-re-dark text-white px-4 py-2 rounded">
                        Login
                    </Link>
                    </div>
                </nav>

            </header>
        </>
    );
};

export default HeaderEn;