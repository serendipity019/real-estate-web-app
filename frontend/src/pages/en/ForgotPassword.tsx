import {useNavigate} from "react-router-dom";
import {Button} from "@/components/ui/button";

const NewPasswordEn = () => {
    const navigate = useNavigate();

    return (
        <>
            <div className="min-h-screen p-8">
                <div className="max-w-4xl mx-auto">
                    <div className="bg-re-lighter p-6 rounded-lg shadow-md">
                        <h1 className="text-2xl font-bold text-gray-900 mb-4">Reset your Password</h1>
                        <p className="text-gray-600 mb-6">Press the button to reset your password!</p>
                        <Button className="bg-blue-500 text-white p-2 rounded hover:bg-re-darker transition"
                        onClick={() => navigate("/")}>
                            Reset your password
                        </Button>    
                    </div>
                </div>
            </div>
        </>
    );

};

export default NewPasswordEn;