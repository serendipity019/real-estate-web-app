import BlBglogo from "../logo/BlBgLogo";
import {MapPin, Phone, Smartphone, Facebook, Instagram} from 'lucide-react';
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
                    <div className="mt-12">
                        <div className="flex gap-4">
                            <span> <Facebook/> 
                            <a href= "https://www.facebook.com/" className="text-gray-300 hover:text-gray-800 ml-2"></a>
                            </span>
                            <span> <Instagram/> 
                                <a href= "https://www.instagram.com/" className="text-gray-300 hover:text-gray-800 ml-2"></a>
                            </span>
                        </div>
                        <div><span className="text-gray-300">Terms of use and privacy policy</span></div>
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