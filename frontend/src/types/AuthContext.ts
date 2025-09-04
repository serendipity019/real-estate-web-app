import { createContext } from "react";

type AuthContextProps = {
    isAuthenticated: boolean;
    login: (token: string) => void;
    logout: () => void;
};

export const AuthContext = createContext<AuthContextProps | undefined>(undefined);