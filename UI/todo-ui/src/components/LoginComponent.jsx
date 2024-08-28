import React, { useState } from 'react'
import { loginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const LoginComponent = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const navigator = useNavigate();

    async function handleLoginForm(e){

        e.preventDefault();

        await loginAPICall(username, password).then((response) => {

            console.log(response.data);
            
            toast.success('Login Successfull');

            const token = 'Basic ' + window.btoa(username + ":" + password);
            storeToken(token);

            saveLoggedInUser(username);
            navigator("/cloudvendor")

            window.location.reload(false);
        }).catch(error => {
            console.error(error);  // Log the error to the console

            // You can also display an error message to the user
            toast.error('Login Failed: ' + error.message);
        })

    }

  return (
    <div className='container'>
        <br /> <br />
        <div className='row'>
            <div className='col-md-6 offset-md-3'>
                <div className='card'>
                    <div className='card-header'>
                        <h2 className='text-center'> Login Form </h2>
                    </div>

                    <div className='card-body'>
                        <form>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Username or Email</label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='username'
                                        className='form-control'
                                        placeholder='Enter username'
                                        value={username}
                                        onChange={ (e) => setUsername(e.target.value)}
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Password </label>
                                <div className='col-md-9'>
                                    <input
                                        type='password'
                                        name='password'
                                        className='form-control'
                                        placeholder='Enter password'
                                        value={password}
                                        onChange={ (e) => setPassword(e.target.value)}
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='form-group mb-3'>
                                <button className='btn btn-primary' onClick={ (e) => handleLoginForm(e)}>Submit</button>

                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </div>


    </div>
  )
}

export default LoginComponent