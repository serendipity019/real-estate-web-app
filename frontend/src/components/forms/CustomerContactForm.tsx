import {useForm} from "react-hook-form"
import { Input } from "../ui/input";
import { Select, SelectItem, SelectTrigger, SelectValue, SelectContent } from "../ui/select";
import { type contactDetailsType, contactForm } from "../../types/ContactSchema";
import { Label } from "../ui/label";
import { zodResolver } from "@hookform/resolvers/zod";

const contactOptions = ["All Day", "Morning", "Afternoon"];

const CustomerContactInfo = () => {
    
    const { register,
            //handleSubmit,
            formState: {errors} } = useForm<contactDetailsType>({
        resolver: zodResolver(contactForm),        
        defaultValues: {
            name: "",
            surname: "",
            email: "",
            phone: "",
            contactHours: "Afternoon"
        }
    });

    return (
        <>
            <h2 className="text-xl font-bold ml-4 text-re-dark p-2">Contact Details</h2>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="grid gap-4">

                <div>
                    <Label className="text-re-dark mb-1" htmlFor="name">Name</Label>
                    <Input {...register("name")} placeholder="Name" />
                    {errors.name && (
                        <p className="text-red-500 text-sm">{errors.name.message}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="surname">Surname</Label>
                    <Input {...register("surname")} placeholder="Surname" />
                    {errors.surname && (
                        <p className="text-red-500 text-sm">{errors.surname.message}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="email">Email</Label>
                    <Input {...register("email")} placeholder="Email" type="email" />
                    {errors.email && (
                        <p className="text-red-500 text-sm">{errors.email.message}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="phone">Phone</Label>
                    <Input {...register("phone")} placeholder="Phone" />
                    {errors.phone && (
                        <p className="text-red-500 text-sm">{errors.phone.message}</p>
                    )}
                </div>
                <div>
                    <Select {...register("contactHours")} onValueChange={(val) => console.log(val)}>
                        <SelectTrigger className="w-full">  {/* Shows the selected value */}
                            <SelectValue placeholder="Select contact hours" />
                        </SelectTrigger>
                        <SelectContent>
                            {contactOptions.map((opt) => (
                                <SelectItem key={opt} value={opt}>
                                    {opt}
                                </SelectItem>
                    ))}
                        </SelectContent>
                    </Select>
                </div>          
                
            </div>
            

            

        </>
    );
};

export default CustomerContactInfo;