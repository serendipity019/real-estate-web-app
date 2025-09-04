import { z} from "zod";

export const loginSchema = z.object({
    email: z.string().email({message: "Invalid email address"}),
    password: z.string().min(6, {message: "Password must be at least 6 characters long"})
});

export type LoginData = z.infer<typeof loginSchema>;

export type AuthResponse = {
    token: string;
    user: {
        id: number;
        name: string;
        surname: string;
        role: "ADMIN" | "USER" | "AGENT";
    };
};

export async function login(data: LoginData): Promise<AuthResponse> {
    const response = await fetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Login failed");
    }

    return await response.json();
}