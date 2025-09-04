import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '@/hooks/useAth';

interface ProtectedRouteProps {
    children: React.ReactNode;
}

const ProtectedRoute = ({ children }: { children: ProtectedRouteProps }) => {
    const token = useAuth();
    const navigate = useNavigate();

    if (!token) {
        return navigate("/login");
    }
    return (
        <>
          {children}
        </>
    );
};

export default ProtectedRoute;