import WhiteBglogo from "../logo/WhiteBgLogo";

const ContactInfo = () => {
    return (
        <>
          <div className="flex flex-col items-center w-[360px] text-white font-semibold bg-[#f6f6f6]">
            <div className="bg-re-light w-[360px] h-[40px] pt-2">Contact Us</div>
            <div className="flex flex-col items-center">
                <div className="items-center w-[320px] h-[240px]"><WhiteBglogo/></div>
                <hr className="h-0.5 bg-re-dark mb-8 w-[300px]" />
                      <span className="text-re-dark">Telephone</span>  
                      <a href="tel:0302104578126" className="text-re-light hover:text-re-dark ml-2"> 
                        (030) 2104578126</a>
                      <span className="text-re-dark mt-2">Mobile</span>
                      <a href="tel:0306978706041" className="text-re-light hover:text-re-dark ml-2"> 
                        (030) 6978706041</a>                                
                      <span className="text-re-dark mt-2">Email</span>
                      <a href= "email: serendipitya137@aueb.gr" className="text-re-light hover:text-re-dark ml-2">serendipitya137@aueb.gr</a>             
                      <span className="text-re-dark mt-2">Address </span>
                      <a href= "https://maps.app.goo.gl/98GTbrz7ZqqdZgsx7" className="text-re-light hover:text-re-dark ml-2">Patision 76, Athens 10434</a>                
            </div>            
          </div>
        </>
    );
};

export default ContactInfo;