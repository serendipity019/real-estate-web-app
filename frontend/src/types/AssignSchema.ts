import {z} from "zod"; 

export const assignForm = z.object({
    region: z.string(),
    county: z.string(),
    category:z.string(),
    type: z.string(),
    status: z.enum(["Rent", "Sale"]),
    price: z.coerce.number().positive(),
    description: z.string().min(7)
});

export type assignPropertyType = z.infer<typeof assignForm>; 