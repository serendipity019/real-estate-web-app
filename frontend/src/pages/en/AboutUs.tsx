import ContactInfo from "@/components/layout/ContactInfo";
import LineLogo from "../../components/logo/LineLogo";

const AboutUsEn = () => {
    return (
        <>
            <div className="flex">
                <LineLogo/>
                <h1 className="text-2xl font-bold ml-4 text-re-dark p-2">About Us</h1>
            </div>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="flex">
                <div className="w-6/10 mr-8">
                    <p className="mt-2">The company "Papapanagiotou Real Estate & Partners" has a dynamic presence in the real estate business sector in the city of Athens, supported by an experienced team of real estate consultants. We have been active in the real estate market since 2021, maintaining an office at 76 Patision Street, Athens. </p>
                    <p className="mt-2">We have built our reputation step-by-step through directness, honesty, and reliability. We are committed to providing high-quality services, ensuring that our clients receive the best possible experience in their real estate transactions. </p>
                    <p className="mt-2">We offer all types of properties, including detached houses, apartments, plots of land, agricultural plots, commercial properties, and more. </p>
                    <p className="mt-2">We provide property management, appraisal, and renovation services. Furthermore, we collaborate with specialized professionals (notaries, lawyers, engineers, architects), ensuring your interests are protected in the best possible way. </p>
                    <p className="mt-2">We listen to your needs and desires, work methodically, and together with our partners, ensure that as our client, you will receive high-quality services that fully cover your needs. </p>
                </div>
                <ContactInfo/>
            </div>
        </>
    );
};

export default AboutUsEn;