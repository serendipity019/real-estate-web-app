import {z} from "zod"; 

export const contactForm = z.object({
    name: z.string().min(2, "required"),
    surname: z.string().min(2, "required"),
    email: z.string().email().min(1, "required"),
    phone: z.string().min(10),
    contactHours: z.enum(["All Day", "Morning", "Afternoon"])
}); 

export const assignForm = z.object({
    region: z.string(),
    county: z.string(),
    category:z.string(),
    type: z.string(),
    status: z.enum(["Rent", "Sale"]),
    price: z.coerce.number().positive(),
    description: z.string().min(7)
});

export type contactDetailsType = z.infer<typeof contactForm>;  
export type assignPropertyType = z.infer<typeof assignForm>; 