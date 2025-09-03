import { useEffect } from "react";
import { Link } from "react-router-dom";

const HomePage = () => { 

    useEffect(() => {
        document.title = "Home Page";
    }, []);

    return (
        <> 
          <div className="text-center">
      <h1 className="text-4xl font-bold text-re-dark mb-8">
        Welcome to Papapanagiotou Real Estate
      </h1>
      <div className="grid md:grid-cols-2 gap-8 max-w-4xl mx-auto">
        <Link to="/assign" className="bg-re-light p-6 rounded-lg hover:bg-re-dark hover:text-white">
          <h2 className="text-2xl font-semibold mb-4">Offer a Property</h2>
          <p>Sell or rent your property with our professional services</p>
        </Link>
        <Link to="/request" className="bg-re-light p-6 rounded-lg hover:bg-re-dark hover:text-white">
          <h2 className="text-2xl font-semibold mb-4">Request a Property</h2>
          <p>Find your dream property with our assistance</p>
        </Link>
      </div>
    </div>
        </>
    );
}

export default HomePage;