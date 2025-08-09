import {z} from "zod"; 

export const contactForm = z.object({
    name: z.string().min(2, "required"),
    surname: z.string().min(2, "required"),
    email: z.string().email().min(1, "required"),
    phone: z.string().min(10).max(13, "Phone number must be between 10 and 13 characters"),
    contactHours: z.enum(["All Day", "Morning", "Afternoon"]).default("Afternoon")
}); 

export type contactDetailsType = z.infer<typeof contactForm>; 