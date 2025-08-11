import LineLogo from "../logo/LineLogo";
import { Input } from "../ui/input";
import { Label } from "../ui/label";
import { Select, SelectValue, SelectContent, SelectTrigger, SelectItem } from "../ui/select";
import {requestSchema, type requestPropertyType} from "../../types/RequestSchema";
import { useForm, Controller } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import  { useState } from "react";
import { regionData } from "../../data/administrative-data"; // Assuming this is the correct
import { categories } from "@/data/category-data"; 
import { Textarea } from "../ui/textarea";
import { Button } from "../ui/button";
import CustomerContactInfo from "./CustomerContactForm";
import { type contactDetailsType, contactForm } from "../../types/ContactSchema";
import ContactInfo from "@/components/layout/ContactInfo"; 

const RequestForm = () => {

     const [selectedRegion, setSelectedRegion] = useState<string>("");
        const [selectedCounty, setSelectedCounty] = useState<string>("");
        const [selectedArea, setSelectedArea] = useState<string>("");
    
        const counties = selectedRegion ? Object.keys(regionData[selectedRegion]) : [];
        const areas = selectedCounty ? regionData[selectedRegion][selectedCounty] : [];
    
        const statusOptions = ["Rent", "Buy"];
    
        const [selectedCategory, setSelectedCategory] = useState<string>("");
        const [selectedType, setSelectedType] = useState<string>("");    
    
        const typeOfProperty = selectedCategory ? categories[selectedCategory] : [];

        const handleContactHoursChange = (value: string) => {
            console.log("contactHours", value);
        };

    const {
            register,
            handleSubmit,
            control,
            formState: { errors }
         } = useForm<requestPropertyType>({
            resolver: zodResolver(requestSchema),
            defaultValues: {
                region: "",
                county: "",
                area: "",
                priceFrom: 0,
                priceTo: 0,
                category: "",
                type: "",
                status: "Rent",
                squareMetersFrom: 0,
                squareMetersTo: 0,
                description: "",
                name: "",
                surname: "",
                email: "",
                phone: "",
                contactHours: "Afternoon"
            }
        });

    return (
        <>
            <div className="flex">
                <LineLogo/>
                <h1 className="text-2xl font-bold ml-4 text-re-dark p-2">Request a Property</h1>
            </div>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div className="flex">
                <div>
                    <div>
                    <p>
                        If you cannot find the property you are looking for, feel free to submit a Property Request. Define the criteria of your Enquiry and let our Real Estate agency know, exactly, what you are looking for and we will contact you as soon as possible. Find your next home quickly and easily. 
                    </p>
                </div>
                <div className="flex">
                    <h1 className="text-xl font-bold ml-4 p-2">Property Enquiry Criteria </h1>
                </div>
                <hr className="h-0.5 bg-re-dark mb-8" />

                <form onSubmit={handleSubmit((data) => console.log(data))} className="space-y-4">
                                <div>
                                    <Label htmlFor="region" className="text-re-dark mb-1">Region</Label>
                                    <Controller
                        name="region"
                        control={control}
                        rules= {{ required: "Region is required" }}
                        render={({ field }) => (
                            <Select
                                onValueChange={(value) => {
                                    field.onChange(value);
                                    setSelectedRegion(value);
                                    setSelectedCounty("");
                                    setSelectedArea("");
                                }}
                                value={field.value} >
                            
                                <SelectTrigger className="w-full">
                                    <SelectValue placeholder="Select Region" />
                                </SelectTrigger>
                                <SelectContent>
                                    {Object.keys(regionData).map((region) => (
                                        <SelectItem key={region} value={region}>
                                            {region}
                                        </SelectItem>
                                    ))}
                                </SelectContent> 
                            </Select>
                        )}
                        />    
                        {errors.region && <p className="text-red-500 text-sm">{errors.region.message}</p>}
                    </div>
                    <div>
                        <Label htmlFor="county" className="text-re-dark mb-1">County</Label>
                        <Controller
                            name="county"
                            control={control}
                            rules={{ required: "County is required" }}
                            render={({ field }) => (
                                <Select
                                    onValueChange={(value) => {
                                        field.onChange(value);
                                        setSelectedCounty(value);
                                        setSelectedArea("");
                                    }}
                                    value={field.value}>
                                    
                                    <SelectTrigger className="w-full">
                                        <SelectValue placeholder="Select County" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {counties.map((county) => (
                                            <SelectItem key={county} value={county}>
                                                {county}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            )}
                        />
                        {errors.county && <p className="text-red-500 text-sm">{errors.county.message}</p>}
                    </div>

                    <div>
                        <Label htmlFor="area" className="text-re-dark mb-1">Area</Label>
                        <Controller
                            name="area"
                            control={control}
                            rules={{ required: "Area is required" }}
                            render={({ field }) => (
                                <Select
                                    onValueChange={(value) => {
                                        field.onChange(value);
                                        setSelectedArea(value);
                                    }}
                                    value={field.value}>
                                    
                                    <SelectTrigger className="w-full">
                                        <SelectValue placeholder="Select Area" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {areas.map((area) => (
                                            <SelectItem key={area} value={area}>
                                                {area}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            )}
                        />
                        {errors.area && <p className="text-red-500 text-sm">{errors.area.message}</p>}
                    </div>

                    <div>
                        <Label className="text-re-dark mb-1" htmlFor="category">Category</Label>
                        <Controller
                            name="category"
                            control={control}
                            rules={{ required: "Category is required" }}
                            render={({ field }) => (
                            <Select  onValueChange={(val) => {
                                field.onChange(val)
                                setSelectedCategory(val);
                                setSelectedType(""); // Reset type when category changes
                            }} 
                            value={field.value}>
                                <SelectTrigger className="w-full">
                                    <SelectValue placeholder="Select category" />
                                </SelectTrigger>
                                <SelectContent>
                                    {Object.keys(categories).map((cat) => (
                                        <SelectItem key={cat} value={cat}>
                                            {cat}
                                        </SelectItem>
                                    ))}
                                </SelectContent>
                            </Select>
                        )}
                        />
                        {errors.category && (
                            <p className="text-red-500 text-sm">{errors.category.message}</p>
                        )}
                    </div>

                    <div>
                        <Label className="text-re-dark mb-1" htmlFor="type">Type of Property</Label>
                        <Controller
                            name="type"
                            control={control}
                            rules={{ required: "Type of property is required" }}
                            render={({ field }) => (
                        <Select  onValueChange={(val) => {
                            field.onChange(val);
                            setSelectedType(val);
                        }}
                            value={field.value}>
                            <SelectTrigger className="w-full">
                                <SelectValue placeholder="Select type of property" />
                            </SelectTrigger>
                            <SelectContent>
                                {typeOfProperty.map((propType) => (
                                    <SelectItem key={propType} value={propType}>
                                        {propType}  
                                    </SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                        )}
                        />
                        {errors.type && (
                            <p className="text-red-500 text-sm">{errors.type.message}</p>
                        )}
                    </div>
                    <div>
                        <Label className="text-re-dark mb-1" htmlFor="price">Price</Label>
                        <div className="flex space-x-2">
                            <Input {...register("priceFrom")} placeholder="from" />
                            {errors.priceFrom && (
                            <p className="text-red-500 text-sm">{errors.priceFrom.message}</p>
                            )}
                            <Input {...register("priceTo")} placeholder="to" />
                            {errors.priceTo && (
                            <p className="text-red-500 text-sm">{errors.priceTo.message}</p>
                            )}
                        </div>
                    </div>
                    <div>
                        <Label className="text-re-dark mb-1" htmlFor="squareMeter">Sq.m</Label>
                        <div className="flex space-x-2">
                            <Input {...register("squareMetersFrom")} placeholder="from" />
                            {errors.squareMetersFrom && (
                            <p className="text-red-500 text-sm">{errors.squareMetersFrom.message}</p>
                            )}
                            <Input {...register("squareMetersTo")} placeholder="to" />
                            {errors.squareMetersTo && (
                            <p className="text-red-500 text-sm">{errors.squareMetersTo.message}</p>
                            )}
                        </div>
                    </div>
                    <div>
                        <Label className="text-re-dark mb-1" htmlFor="status">To</Label>
                        <Select {...register("status")} defaultValue="Rent" onValueChange={(val) => console.log(val)}>
                            <SelectTrigger className="w-full">
                                <SelectValue placeholder="Select purpose of use" />
                            </SelectTrigger>
                            <SelectContent>
                                {statusOptions.map((opt) => (
                                    <SelectItem key={opt} value={opt}>
                                        {opt}
                                    </SelectItem>
                                ))}
                            </SelectContent>
                        </Select>    
                    </div>
                    <div>
                        <Label className="text-re-dark mb-1" htmlFor="description">Description</Label>
                        <Textarea {...register("description")} placeholder="Description of the property" />
                    </div>
                    <div>
                        <CustomerContactInfo 
                            register={register} // And here i have critical error
                            errors={errors}
                            onContactHoursChange={handleContactHoursChange} />
                    </div>

                    <p className="mb-0.5 mt-1">Please, fill in the form and then Click the Send button. Fields marked with are mandatory fields</p>    
                    <div>
                        <Button type="submit" className="bg-re-darker text-white hover:bg-re-light">
                            Send
                        </Button>    
                    </div>
                </form> 
                </div>
                <div className="hidden md:block">
                        <ContactInfo/>
                </div>                
            </div>
        </>
    );
};

export default RequestForm;
