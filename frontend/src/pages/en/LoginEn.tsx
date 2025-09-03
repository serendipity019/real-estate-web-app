import { z} from "zod";
import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import {Eye, EyeOff} from "lucide-react";
import  BlBglogo from "@/components/logo/BlBgLogo";
const loginSchema = z.object({
    email: z.string().email({message: "Invalid email address"}),
    password: z.string().min(6, {message: "Password must be at least 6 characters long"})
});

type LoginData = z.infer<typeof loginSchema>;

const LoginEn = () => {
    const [showPassword, setShowPassword] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const {
        register,
        handleSubmit,
        formState: { errors },
        setError
    } = useForm<LoginData>({
        resolver: zodResolver(loginSchema),
        defaultValues: { 
            email: "",
            password: "" }
    });

    const onSubmit = async (data: LoginData) => {
        setIsLoading(true);
        // try {
        //     const response = await fetch("/api/auth/login", {
        //         method: "POST",
        //         headers: { "Content-Type": "application/json" },
        //         body: JSON.stringify(data)
        //     });
        //     if (response.ok) {
        //         navigate("/");
        //     } else {
        //         const errorData = await response.json();
        //         alert(errorData.message || "Login failed");
        //     }
        // } catch (error) {
        //     alert("An error occurred. Please try again.");
        // } finally {
        //     setIsLoading(false);
        // }

        {/* Demo login logic */}
        try {
            console.log("Logging in with", data);
        await new Promise((resolve) => setTimeout(resolve, 1000));

        localStorage.setItem("token", "mock-jwt-token");
        localStorage.setItem("user", JSON.stringify({ 
            id: 1,
            email: data.email,
            name: "Admin",
            role: "ADMIN"
        }));
        navigate("/");
        } catch (error) { setError("root", {
        type: "manual",
        message: "Invalid email or password"
      });
      } finally {
      setIsLoading(false);
      }
        
    };

    return (
        <>
          <div className="flex flex-col items-center">
            <BlBglogo/>
            <h1 className="text-4xl font-bold text-center text-re-dark mt-5">Login to Your Account</h1>
          </div>  
          <form onSubmit={handleSubmit(onSubmit)} className="max-w-md mx-auto mt-5 p-6 border rounded-lg shadow-md bg-white">
            <div>
                <Label htmlFor="email" className="block mb-2 font-medium text-gray-700">
                Email Address
                </Label>
                <Input
                type="email"
                id="email"
                autoComplete="email"
                {...register("email")}
                placeholder="email"
                className="w-full p-2 border border-gray-300 rounded mb-2"
                />
                {errors.email && <p className="text-red-500 text-sm mb-2">{errors.email.message}</p>}
            </div>
            <div className="mt-4">
                <Label htmlFor="password" className="block mb-2 font-medium text-gray-700">
                Password
                </Label>
                <div className="relative">
                <Input
                    type={showPassword ? "text" : "password"}
                    id="password"
                    autoComplete="current-password"
                    {...register("password")}
                    placeholder="password"
                    className="w-full p-2 border border-gray-300 rounded mb-2"
                />
                <button
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                    className="absolute right-2 top-2 text-sm text-blue-500"
                >
                    {showPassword ? <EyeOff /> : <Eye />}
                </button>
                </div>
                {errors.password && <p className="text-red-500 text-sm mb-2">{errors.password.message}</p>}

                {errors.root && <p className="text-red-500 text-sm mb-2">{errors.root.message}</p>}
                <div>
                    <Button type="submit" disabled={isLoading} className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600 transition">
                    {isLoading ? "Logging in..." : "Login"}
                    </Button>
                </div>
                <div className="mt-4 text-center">
                    <Link to="/forgot-password" className="text-sm text-blue-500 hover:underline">
                    Forgot your Password?
                    </Link>
                </div>    
            </div>     
            </form>
                {/* Demo credentials. I must remember to remove this in production */}
            <div className="max-w-md mx-auto mt-4 p-4 border rounded-lg shadow-md bg-white text-center">
                <h3 className="text-sm font-medium mb-2">Demo Credentials</h3>
                <p className="text-xm">Email: admin@realestate.com</p>
                <p className="text-xm">Password: admin1234</p>            
            </div>             
        </>
    );
}; 

export default LoginEn;