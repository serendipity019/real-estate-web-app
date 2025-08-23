import {z} from "zod"; 
import { contactForm } from "./ContactSchema";

export const assignForm = z.object({
    region: z.string().min(1, "Region is required"),
    county: z.string().min(1, "County is required"),
    area: z.string().min(1, "Area is required"),
    price: z.coerce.number().positive(),
    category:z.string().min(1, "Category is required"),
    type: z.string().min(1, "Property type is required"),
    status: z.enum(["Rent", "Sale"]),
    squareMeters: z.coerce.number().positive(),
    description: z.string().min(7)
});

//Here i make the merge between the contact form and the assign form
export const assignSchema = assignForm.merge(contactForm);

export type assignPropertyType = z.infer<typeof assignSchema>; 

export type assignOnlyType = z.infer<typeof assignForm>;