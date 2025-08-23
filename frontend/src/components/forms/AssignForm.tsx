// need to change the name of this file to AssignPropertyPage.tsx
import {assignSchema, type assignPropertyType} from "../../types/AssignSchema";
import { useForm, Controller } from "react-hook-form";
import  { useState } from "react";
import { regionData } from "../../data/administrative-data"; // Assuming this is the correct
import LineLogo from "../logo/LineLogo";
import { Input } from "../ui/input";
import { Label } from "../ui/label";
import { Select, SelectValue, SelectContent, SelectTrigger, SelectItem } from "../ui/select";
import { Textarea } from "../ui/textarea";
import CustomerContactInfo from "./CustomerContactForm";
import { categories } from "@/data/category-data"; 
import { zodResolver } from "@hookform/resolvers/zod";
import { type contactDetailsType, contactForm } from "../../types/ContactSchema";
import { Button } from "../ui/button";


const AssignPropertyPage = () => { 
    const [selectedRegion, setSelectedRegion] = useState<string>("");
    const [selectedCounty, setSelectedCounty] = useState<string>("");
    const [selectedArea, setSelectedArea] = useState<string>("");

    const counties = selectedRegion ? Object.keys(regionData[selectedRegion]) : [];
    const areas = selectedCounty ? regionData[selectedRegion][selectedCounty] : [];

    const statusOptions = ["Rent", "Sale"];

    const [selectedCategory, setSelectedCategory] = useState<string>("");
    const [selectedType, setSelectedType] = useState<string>("");    

    const typeOfProperty = selectedCategory ? categories[selectedCategory] : [];
    //something i have wrong above. I have to print the content of the typeOfProperty and not the numbers
    const handleContactHoursChange = (value: string) => {
        console.log("contactHours", value);
    }

    const {
        register,
        handleSubmit,
        control,
        formState: { errors }
     } = useForm<assignPropertyType>({
        resolver: zodResolver(assignSchema),
        defaultValues: {
            region: "",
            county: "",
            area: "",
            price: 0,
            category: "",
            type: "",
            status: "Rent",
            squareMeters: 0,
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
                <h1 className="text-2xl font-bold ml-4 text-re-dark p-2">Offer a Property</h1>
            </div>
            <hr className="h-0.5 bg-re-dark mb-8" />
            <div>
                <p>
                    Fill in the following form and we will help you sell or rent your property. Your information will be processed by us promptly and we will then contact you to arrange the details of our cooperation. 
                </p>
            </div>
            <div className="flex">
                <h1 className="text-xl font-bold ml-4 p-2">Property details</h1>
            </div>
            <hr className="h-0.5 bg-re-dark mb-8" />

            <form onSubmit={handleSubmit((data) => console.log(data))} className="space-y-4">
                <div>
                    <Label htmlFor="region" className="text-re-dark mb-1">Region</Label>
                    {/* <select
                        {...register("region", { required: "Region is required" })}
                        value={selectedRegion}
                        onChange={(e) => {
                            setSelectedRegion(e.target.value);
                            setSelectedCounty("");
                            setSelectedArea("");
                        }}
                        className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
                    >
                        <option value="">Select Region</option>
                        {Object.keys(regionData).map((region) => (
                            <option key={region} value={region}>
                                {region}
                            </option>
                        ))}
                    </select> */}

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
                    {/* <select
                        {...register("county", { required: "County is required" })}
                        value={selectedCounty}
                        onChange={(e) => {
                            setSelectedCounty(e.target.value);
                            setSelectedArea("");
                        }}
                        className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
                    >
                        <option value="">Select County</option>
                        {counties.map((county) => (
                            <option key={county} value={county}>
                                {county}
                            </option>
                        ))}
                    </select> */}
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
                    {/* <select
                        {...register("area", { required: "Area is required" })}
                        value={selectedArea}
                        onChange={(e) => setSelectedArea(e.target.value)}
                        className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500">
                        <option value="">Select Area</option>
                        {areas.map((area) => (
                            <option key={area} value={area}>
                                {area}
                            </option>
                        ))}
                    </select> */}
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
                    <Label className="text-re-dark mb-1" htmlFor="price">Price</Label>
                    <Input {...register("price")} placeholder="Price" />
                    {errors.price && (
                        <p className="text-red-500 text-sm">{errors.price.message}</p>
                    )}
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
                    <Label className="text-re-dark mb-1" htmlFor="status">For</Label>
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
                    <Label className="text-re-dark mb-1" htmlFor="squareMeters">Sq.m</Label>
                    <Input {...register("squareMeters")} placeholder="Square meters of the property" />
                    {errors.squareMeters && (
                        <p className="text-red-500 text-sm">{errors.squareMeters.message}</p>
                    )}
                </div>
                <div>
                    <Label className="text-re-dark mb-1" htmlFor="description">Description</Label>
                    <Textarea {...register("description")} placeholder="Description of the property" />
                </div>

                <div>
                    <CustomerContactInfo<assignPropertyType> 
                         register={register}
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
        </>
    );
};

export default AssignPropertyPage;