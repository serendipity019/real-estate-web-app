import {type UseFormRegister, type FieldErrors, type FieldValues} from "react-hook-form"
import { Input } from "../ui/input";
import { Select, SelectItem, SelectTrigger, SelectValue, SelectContent } from "../ui/select";
import { Label } from "../ui/label";
import {type Path} from "react-hook-form";

const contactOptions = ["All Day", "Morning", "Afternoon"];

// type Props = {
//     register: ReturnType<typeof useForm<contactDetailsType>>['register'];
//     errors: ReturnType<typeof useForm<contactDetailsType>>['formState']['errors'];
//     onContactHoursChange?: (value: string) => void;
// };
type Props<TFormValues extends FieldValues> = {
    register: UseFormRegister<TFormValues>;
    errors: FieldErrors<TFormValues>;
    onContactHoursChange?: (value: string) => void;
};

const CustomerContactInfo = <TFormValues extends FieldValues>({ register, errors, onContactHoursChange }: Props<TFormValues>) => {

    return (
        <>
            <h2 className="text-xl font-bold ml-4 text-re-dark p-2">Contact Details</h2>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="grid gap-4">

                <div>
                    <Label className="text-re-dark mb-1" htmlFor="name">Name</Label>
                    <Input {...register("name" as Path<TFormValues>, {required: "Name is required"})} placeholder="Name" />
                    {errors.name && (
                        <p className="text-red-500 text-sm">{errors.name.message as string}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="surname">Surname</Label>
                    <Input {...register("surname" as Path<TFormValues>, {required: "Surname is required"})} placeholder="Surname" />
                    {errors.surname && (
                        <p className="text-red-500 text-sm">{errors.surname.message as string}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="email">Email</Label>
                    <Input {...register("email" as Path<TFormValues>, {required: "Email is required"})} placeholder="Email" type="email" />
                    {errors.email && (
                        <p className="text-red-500 text-sm">{errors.email.message as string}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="phone">Phone</Label>
                    <Input {...register("phone" as Path<TFormValues>, {required: "Phonenumber is required"})} placeholder="e.g: 0306987455174" />
                    {errors.phone && (
                        <p className="text-red-500 text-sm">{errors.phone.message as string}</p>
                    )}
                </div>
                <div>
                    {/* <Select {...register("contactHours")} onValueChange={(val) => console.log(val)}> */}
                    <Label className="text-re-dark mb-1" htmlFor="contactHours">Contact Hours</Label>
                    <Select onValueChange={onContactHoursChange}>
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