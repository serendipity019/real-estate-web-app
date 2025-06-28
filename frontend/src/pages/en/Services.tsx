import ContactInfo from "@/components/layout/ContactInfo";
import LineLogo from "../../components/logo/LineLogo";

const ServicesEn = () => {
    return (
        <>
            <div className="flex">
                <LineLogo/>
                <h1 className="text-2xl font-bold ml-4 text-re-dark p-2">Services</h1>
            </div>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="flex">
                <div className="w-6/10 mr-8">
                    <h4>The services provided by "Papapanagiotou Real Estate & Partners" are as follows: </h4>
                    <ul className="list-disc pl-10 space-y-2"> 
                        <li className="mt-2">Sales - Rentals of residential properties (studios, apartments, maisonettes, detached houses, etc.)</li>
                        <li className="mt-2">Sales - Rentals of commercial spaces (shops, offices, craft and industrial spaces, etc.)</li>
                        <li className="mt-2">Sales - Rentals of land (plots, agricultural plots)</li>
                        <li className="mt-2">Property appraisal & promotion</li>
                        <li className="mt-2">Property utilization and management</li>
                        <li className="mt-2">Issuance of housing loans</li>
                    </ul>
                </div>
                <div className="hidden md:block">
                    <ContactInfo/>
                </div>
            </div>
        </>
    );
};

export default ServicesEn;