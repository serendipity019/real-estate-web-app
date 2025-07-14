import {useForm, useFormContext} from "react-hook-form"
import { Input } from "../ui/input";
import { Select, SelectItem } from "../ui/select";


const contactOptions = ["All Day", "Morning", "Afternoon"];

const CustomerContactInfo = () => {
    const {register} = useFormContext();

    const form = useForm<Z.infer<typeof contactDetailsType>>

    return (
        <>
            <h2 className="text-xl font-bold ml-4 text-re-dark p-2">Contact Details</h2>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="grid gap-4">
                <Input {...register("name")} placeholder="Name" />
                <Input {...register("surname")} placeholder="Surname" />
                <Input {...register("email")} placeholder="Email" type="email" />
                <Input {...register("phone")} placeholder="Phone" />
                <Select {...register("contactHours")} >
                    {contactOptions.map((opt) => (
                        <SelectItem key={opt} value={opt}>{opt}</SelectItem>  
                    ))}
                </Select>
                
            </div>
            

            

        </>
    );
};

export default CustomerContactInfo; 