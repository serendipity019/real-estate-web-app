import BlBglogo from "../logo/BlBgLogo";
import {MapPin, Phone, Smartphone, Facebook, Instagram} from 'lucide-react';
import {Link} from "react-router-dom";
const currentYear: number = new Date().getFullYear();

const Footer = () => { 
    return ( 
        <>
            <footer className="">
                <div className="bg-re-light flex justify-between ">
                    <div className="items-start w-[320px] h-[240px]"><BlBglogo/></div>
                    <div className="">
                        <div className="my-8 mt-12">
                            <span className="flex"><MapPin/>
                            <a href= "https://maps.app.goo.gl/98GTbrz7ZqqdZgsx7" className="text-gray-300 hover:text-gray-800 ml-2">Patision 76, Athens 10434</a>
                            </span>
                        </div>
                        <div className="flex gap-2">
                            <span className="flex mr-2 "><Phone className="text-black"/> <span className="text-gray-300 ml-2">(030)2104578126</span></span>
                            <span className="flex"><Smartphone/>
                            <span className="text-gray-300 ml-2"> (030) 6978706041</span></span>
                        </div>
                        <div className="my-8">
                            <span className="flex"><MapPin/>
                            <a href= "serendipitya137@aueb.gr" className="text-gray-300 hover:text-gray-800 ml-2">serendipitya137@aueb.gr</a>
                            </span>
                        </div>
                    </div>
                    <div className="mt-12 mr-4">
                        <div className="flex gap-4 pl-20">
                            <span> 
                            <a href= "https://www.facebook.com/" className="text-gray-300 hover:text-gray-800 ml-2"><Facebook/> </a>
                            </span>
                            <span> 
                                <a href= "https://www.instagram.com/" className="text-gray-300 hover:text-gray-800 ml-2"><Instagram/> </a>
                            </span>
                        </div>
                        <div className="mt-8 ">
                            <span className="text-gray-300">
                            < Link to="../../pages/gr/TermsOfUse">Terms of use and privacy policy</Link>
                            </span></div>                        
                        </div>
                </div>
                <div className="bg-re-dark text-center text-gray-300 py-4" >
                    Copyright © {currentYear} by Papapanagiotou Real Estate - Powered by <span className="text-white">Papapanagiotou Panagiotis</span>. All rights reserved.
                </div>
            </footer>
        </>
    );

}

export default Footer;