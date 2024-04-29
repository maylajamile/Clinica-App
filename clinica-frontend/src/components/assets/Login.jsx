import React, { useState } from 'react';
import './Login.css';
import user_icon from '../assets/images/person.png';
import password_icon from '../assets/images/password.png';
import { setToken } from '../../axios/axios_helper';
import { jwtDecode } from 'jwt-decode';
import LoggedView from './LoggedView';
import { request } from '../../axios/axios_helper';


const Login = () => {
  
    const [login, setLogin] = useState('');
    const [senha, setSenha] = useState('');
    const [error, setError] = useState('');
    const [userRole, setUserRole] = useState('');
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const getRoleDeToken = (token) => {
        const decoded = jwtDecode(token);
        return decoded.scope;
    };

    const handleLogin = async () => {
        try {
            const response = await request('post', '/auth/login', { login, senha });
            const token = response.data.token;
            setToken(token);
            const userRole = getRoleDeToken(token);
            setUserRole(userRole);
            setIsLoggedIn(true);
        } catch (error) {
            setError('Usuário ou Senha incorretos.');
        }
    };
    

    return (
        <>
            {isLoggedIn ? (
                <LoggedView userRole={userRole}/>
            ) : (
                <div className="container-login">
                    <div className="header-login">
                        <div className="text-login">Login</div>
                        <div className="underline"></div>
                    </div>
                    <div className="inputs">
                        <div className="input">
                            <img src={user_icon} alt="Imagem input usuário" />
                            <input type="text" placeholder="Nome" value={login} onChange={(e) => setLogin(e.target.value)}/>
                        </div>
                        <div className="input">
                            <img src={password_icon} alt="Imagem input senha" />
                            <input type="password" placeholder="Senha" value={senha} onChange={(e) => setSenha(e.target.value)}/>
                        </div>
                    </div>
                    <div className="submit-container">
                        <div className="submit" onClick={handleLogin}>Login</div>
                    </div>
                    {error && <div className="error-message">{error}</div>}
                </div>
            )}
        </>
    );    
};

export default Login;
