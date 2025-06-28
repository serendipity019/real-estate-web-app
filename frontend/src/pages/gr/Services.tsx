import ContactInfoEl from "@/components/layout/ContactInfoEl";
import LineLogo from "../../components/logo/LineLogo";

const ServicesGr = () => {
    return (
        <>
            <div className="flex">
                <LineLogo/>
                <h1 className="text-2xl font-bold ml-4 text-re-dark p-2">Υπηρεσίες</h1>
            </div>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="flex">
                <div className="w-6/10 mr-8">
                    <h4>Οι Υπηρεσίες που παρέχει η "Papapanagiotou Real Estate & Συνεργάτες" είναι οι εξής: </h4>
                    <ul className="list-disc pl-10 space-y-2"> 
                        <li className="mt-2">Πωλήσεις – Ενοικιάσεις κατοικιών (γκαρσονιέρες, διαμερίσματα, μεζονέτες, μονοκατοικίες, κ.α.)</li>
                        <li className="mt-2">Πωλήσεις – Ενοικιάσεις επαγγελματικών χώρων (καταστήματα, γραφεία, βιοτεχνικοί και βιομηχανικοί χώροι κ.α.)</li>
                        <li className="mt-2">Πωλήσεις – Ενοικιάσεις εκτάσεων (οικόπεδα, αγροτεμάχια)</li>
                        <li className="mt-2">Εκτίμηση & προώθηση Ακινήτων</li>
                        <li className="mt-2">Αξιοποίηση και διαχείριση ακινήτων</li>
                        <li className="mt-2">Έκδοση Στεγαστικών Δανείων</li>
                    </ul>
                </div>
                <div className="hidden md:block">
                    <ContactInfoEl/>
                </div>
            </div>
        </>
    );
};

export default ServicesGr;