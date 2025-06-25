import { useEffect } from "react";
import { Link } from "react-router";

const NotFoundPage = () => { 

    useEffect(() => {
        document.title = "Page Not Found - 404";
    }, []);

    return (
        <> 
          <div className="text-center">
            <h1 className="text-center font-bold mt-8 text-9xl text-cf-dark-red ">404</h1>
            <p className="text-4xl text-cf-dark-gray">Page not Found</p>
            <p className="text-lg text-cf-gray mt-40 mb-40">The page you are looking for, dosen't exists</p>
            <Link to="/" className="bg-cf-dark-red text-white px-4 py-2 rounded">Go back to Home</Link>
          </div>
        </>
    );
}

export default NotFoundPage;