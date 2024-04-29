import React, { useState, useEffect } from 'react';
import { request } from '../../axios/axios_helper';
import './LoggedView.css';
import 'bootstrap/dist/css/bootstrap.min.css';

const LoggedView = ({ userRole }) => {
    const [pacientes, setPacientes] = useState([]);
    const [enfermeiros, setEnfermeiros] = useState([]);
    const [showPopup, setShowPopup] = useState(false);
    const [error, setError] = useState('');
    const [novoPacienteData, setNovoPacienteData] = useState({
        nome: '',
        cpf: '',
        dataNascimento: '',
        peso: '',
        altura: '',
        uf: ''
    });

    useEffect(() => {
        handleListaDePacientes();
        if (userRole === "MEDICO") {
            handleListaDeEnfermeiros();
        }
    }, [userRole]);

    const handleCadastro = async () => {
        try {
            const response = await request('post', '/clinica/cadastrar/paciente', novoPacienteData);
            handleListaDePacientes();
            setShowPopup(false);
            setNovoPacienteData({
                nome: '',
                cpf: '',
                dataNascimento: '',
                peso: '',
                altura: '',
                uf: ''
            });
        } catch (error) {
            setError('Faltando informações ou CPF já em uso.');
        }
    };

    const handleListaDePacientes = async () => {
        const response = await request('get', '/clinica/listar/pacientes');
        setPacientes(response.data);
    };

    const handleListaDeEnfermeiros = async () => {
        const response = await request('get', '/clinica/listar/enfermeiros');
        setEnfermeiros(response.data);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNovoPacienteData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

    return (
        <div className="container-logged">
            <div className="overlay" style={{ display: showPopup ? 'block' : 'none' }}></div> 
            <div className="lista">
                <div className="text">Listagem de Pacientes:</div>
                <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Data de Nascimento</th>
                            <th scope="col">Altura</th>
                            <th scope="col">Peso</th>
                            <th scope="col">UF</th>
                        </tr>
                    </thead>
                    <tbody>
                        {pacientes.map(paciente => (
                            <tr key={paciente.id}>
                                <td>{paciente.id}</td>
                                <td>{paciente.nome}</td>
                                <td>{paciente.dataNascimento}</td>
                                <td>{paciente.altura}</td>
                                <td>{paciente.peso}</td>
                                <td>{paciente.uf}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <div className="submit-cadastro" onClick={() => setShowPopup(true)}>Adicionar novo paciente</div>
            {showPopup && (
                <div className="popup">
                    <div className="popup-inner">
                        <div className="text">Cadastrar novo paciente</div>
                        <div className="inputs">
                            <input type="text" name="nome" value={novoPacienteData.nome} onChange={handleInputChange} placeholder="Nome" />
                            <input type="text" name="cpf" value={novoPacienteData.cpf} onChange={handleInputChange} placeholder="CPF" />
                            <input type="text" name="dataNascimento" value={novoPacienteData.dataNascimento} onChange={handleInputChange} placeholder="Data de Nascimento" />
                            <input type="text" name="peso" value={novoPacienteData.peso} onChange={handleInputChange} placeholder="Peso" />
                            <input type="text" name="altura" value={novoPacienteData.altura} onChange={handleInputChange} placeholder="Altura" />
                            <input type="text" name="uf" value={novoPacienteData.uf} onChange={handleInputChange} placeholder="UF" />
                        </div>
                        <div className="popup-buttons">
                            <button className="submit-popup" onClick={handleCadastro}>Cadastrar</button>
                            <button className="submit-popup" onClick={() => setShowPopup(false)}>Cancelar</button>
                        </div>
                    </div>
                    <div>
                        {error && <div className="error-message">{error}</div>}
                    </div>
                </div>
            )}
            {userRole === "MEDICO" && (
                <>
                    <div className="lista">
                        <div className="text">Listagem de Enfermeiros:</div>
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Nome</th>
                                </tr>
                            </thead>
                            <tbody>
                                {enfermeiros.map(enfermeiro => (
                                    <tr key={enfermeiro.id}>
                                        <td>{enfermeiro.id}</td>
                                        <td>{enfermeiro.nome}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </>
            )}
        </div>
    );
};

export default LoggedView;
