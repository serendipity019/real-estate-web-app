import type { LoginData } from "@/api/login";
import { createContext } from "react";

type AuthContextProps = {
    isAuthenticated: boolean;
    loginUser: (fields: LoginData) => Promise<void> //(token: string) => void;
    logoutUser: () => void;
    role?: string;
    firstname?: string;
};

export const AuthContext = createContext<AuthContextProps | undefined>(undefined);