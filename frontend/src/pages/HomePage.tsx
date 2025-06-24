import { useEffect } from "react";

const HomePage = () => { 

    useEffect(() => {
        document.title = "Home Page";
    }, []);

    return (
        <> 
          <h1 className="text-center text-bold mt-8 text-xl ">Welcome to the Home Page</h1>
        </>
    );
}

export default HomePage;