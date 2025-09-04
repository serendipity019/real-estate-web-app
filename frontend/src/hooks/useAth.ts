import { useContext } from "react";
import { AuthContext } from "@/types/AuthContext";

export function useAuth() {
    const ctx = useContext(AuthContext);
    if (!ctx) {
        throw new Error("user Authenticator must be used within an Authenticator Provider");
    }
    return ctx;
};