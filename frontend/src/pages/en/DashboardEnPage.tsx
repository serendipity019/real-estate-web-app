import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

const DashboardPage = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        navigate("/login");
    }

    return (
        <>
            <div className="min-h-screen p-8">
                <div className="max-w-4xl mx-auto">
                    <div className="bg-re-lighter p-6 rounded-lg shadow-md">
                        <h1 className="text-2xl font-bold text-gray-900 mb-4">DashboardPage</h1>
                        <p className="text-gray-600 mb-6">Welcome to the Dashboard!</p>
                        <Button className="bg-blue-500 text-white p-2 rounded hover:bg-blue-600 transition"
                        onClick={handleLogout}>
                            Logout
                        </Button>    
                    </div>
                </div>
            </div>
        </>
    );

};

export default DashboardPage;