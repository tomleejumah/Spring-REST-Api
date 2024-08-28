import { useState } from 'react'
import './App.css'
// import 'react-toastify/dist/ReactToastify.css';
import ListTodoComponent from './components/ListTodoComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import TodoComponent from './components/TodoComponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { ToastContainer } from 'react-toastify';
import { isUserLoggedIn } from './services/AuthService'

function App() {

  function AuthenticatedRoute({ children }) {
    return isUserLoggedIn() ? children : <Navigate to="/" />;
  }

  return (
    <>
    <BrowserRouter>
    <ToastContainer />
        <HeaderComponent />
          <Routes>
              {/* http://localhost:8080 */}
              <Route path='/' element = { <LoginComponent /> }></Route>
               {/* http://localhost:8080/cloudvendor */}
              <Route path='/cloudvendor' element = { 
              <AuthenticatedRoute>
                <ListTodoComponent />
              </AuthenticatedRoute> 
              }></Route>
              {/* http://localhost:8080/cloudvendor */}
              <Route path='/add-cloudvendor' element = { 
                <AuthenticatedRoute>
                <TodoComponent /> 
                </AuthenticatedRoute>
              }></Route>
              {/* http://localhost:8080/cloudvendor/1 */}
              <Route path='/update-cloudvendor/:id' element = { 
              <AuthenticatedRoute>
              <TodoComponent /> 
              </AuthenticatedRoute>
              }></Route>
               {/* http://localhost:8080/cloudvendor/register */}
              <Route path='/register' element = { <RegisterComponent />}></Route>

               {/* http://localhost:8080/cloudvendor/login */}
               <Route path='/login' element = { <LoginComponent /> }></Route>

          </Routes>
        {/* <FooterComponent /> */}
        </BrowserRouter>
    </>
  )
}

export default App
