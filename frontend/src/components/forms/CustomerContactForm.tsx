import {type UseFormRegister, type FieldErrors, type FieldValues, type UseFormWatch} from "react-hook-form"
import {useState} from "react";
import { Input } from "../ui/input";
import { Select, SelectItem, SelectTrigger, SelectValue, SelectContent } from "../ui/select";
import { Checkbox } from "../ui/checkbox";
import { Label } from "../ui/label";
import {type Path} from "react-hook-form";
import ReqStar from "../logo/RequiredStar";

const contactOptions = ["All Day", "Morning", "Afternoon"];

type Props<TFormValues extends FieldValues> = {
    register: UseFormRegister<TFormValues>;
    errors: FieldErrors<TFormValues>;
    watch?: UseFormWatch<TFormValues>;
    onContactHoursChange?: (value: string) => void;
    parent: "assign" | "request";
};

const CustomerContactInfo = <TFormValues extends FieldValues>({ register, errors, watch, onContactHoursChange, parent }: Props<TFormValues>) => {
    const [showAuthFields, setShowAuthFields] = useState(false);

    const password = watch ? watch("password" as Path<TFormValues>) : "";

    return (
        <>
            <h2 className="text-xl font-bold ml-4 text-re-dark p-2">Contact Details</h2>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="grid gap-4">

                <div>
                    <Label className="text-re-dark mb-1" htmlFor="name">Name<ReqStar/></Label>
                    <Input {...register("name" as Path<TFormValues>, {required: "Name is required"})} placeholder="Name" />
                    {errors.name && (
                        <p className="text-red-500 text-sm">{errors.name.message as string}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="surname">Surname<ReqStar/></Label>
                    <Input {...register("surname" as Path<TFormValues>, {required: "Surname is required"})} placeholder="Surname" />
                    {errors.surname && (
                        <p className="text-red-500 text-sm">{errors.surname.message as string}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="email">Email<ReqStar/></Label>
                    <Input {...register("email" as Path<TFormValues>, {required: "Email is required"})} placeholder="Email" type="email" />
                    {errors.email && (
                        <p className="text-red-500 text-sm">{errors.email.message as string}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="phone">Phone<ReqStar/></Label>
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

                <div className="flex items-center space-x-2 mt-4">
                    <Checkbox 
                        id = "signUpCheckbox"
                        checked = {showAuthFields}
                        onCheckedChange = {(checked) => setShowAuthFields(checked === true)}
                    />
                    <Label htmlFor="signUpCheckbox" className="text-re-dark">Sign up for more options and future management of your {parent}</Label> {/* Later the word request or assign to change consider the parent */}
                </div>          
                {showAuthFields && (
                    <>
                        <p className="text-sm text-gray-500 mt-2">
                        Creating an account will allow you to track the status of your {parent} and manage your properties easily.
                        </p>
                        <div>
                            <Label className="text-re-dark mb-1" htmlFor="password">Password</Label>
                            <Input 
                                {...register("password" as Path<TFormValues>, {
                                    minLength: {
                                        value: 6,
                                        message: "Password must be at least 6 characters long"
                                    }
                                })} 
                                placeholder="Password" 
                                type="password" 
                            />
                            {errors.password && (
                                <p className="text-red-500 text-sm">{errors.password.message as string}</p>
                            )}
                        </div>
                        <div>
                            <Label className="text-re-dark mb-1" htmlFor="confirmPassword">Confirm Password</Label>
                            <Input 
                                {...register("confirmPassword" as Path<TFormValues>, {
                                    validate: (value) => 
                                        value === password || "Passwords do not match",
                                    minLength: {
                                        value: 6,
                                        message: "Password must be at least 6 characters long"
                                    }
                                })} 
                                placeholder="Confirm Password" 
                                type="password" 
                            />
                            {errors.confirmPassword && (
                                <p className="text-red-500 text-sm">{errors.confirmPassword.message as string}</p>
                            )}
                        </div>
                    </>
                )}
                
            </div>                      
        </>
    );
};

export default CustomerContactInfo;