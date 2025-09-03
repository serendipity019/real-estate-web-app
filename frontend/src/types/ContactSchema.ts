import {z} from "zod"; 

export const contactFormBase = z.object({
    name: z.string().min(2, "required"),
    surname: z.string().min(2, "required"),
    email: z.string().email().min(1, "required"),
    phone: z.string().min(10).max(13, "Phone number must be between 10 and 13 characters"),
    contactHours: z.enum(["All Day", "Morning", "Afternoon"]),
    password: z.string().min(6, "Password must be at least 6 characters long").optional().or(z.literal("")),
    confirmPassword: z.string().min(6, "Password must be at least 6 characters long").optional().or(z.literal(""))    
}); 

    
export const contactForm = contactFormBase.refine((data) => {
        if(data.password || data.confirmPassword){
            return data.password === data.confirmPassword;
        }
        return true; 
    }, {message: "Passwords don't match", path: ["confirmPassword"]});

export type contactDetailsType = z.infer<typeof contactForm>; 