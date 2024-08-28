import React, { useEffect, useState } from 'react'
import { completeTodo, deleteTodo, getAllTodos, inCompleteTodo } from '../services/TodoService'
import { useNavigate } from 'react-router-dom'
import Card from './Card'

const ListTodoComponent = () => {

    const [todos, setTodos] = useState([])
    const [cloudvendor, setCloudVendor] = useState([])

    const navigate = useNavigate()


    useEffect(() => {
        listTodos();
    }, [])
    
    function listTodos(){
        getAllTodos().then((response) => {
            setCloudVendor(response.data)
            // setTodos(response.data);
            console.log(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewTodo(){
        navigate('/add-cloudvendor')

    }

    function updateTodo(id){
        console.log(id)
        navigate(`/update-cloudvendor/${id}`)
    }
    
    function removeTodo(id){
        deleteTodo(id).then((response) => {
            listTodos();
        }).catch(error => {
            console.error(error)
        })
    }

    function markCompleteTodo(id){
        completeTodo(id).then((response) => {
            listTodos()
        }).catch(error => {
            console.error(error)
        })
    }

    function markInCompleteTodo(id){
        inCompleteTodo(id).then((response) => {
            listTodos();
        }).catch(error => {
            console.error(error)
        })
    }

  return (

    <div className='container'>
    <h2 className='text-center'>List of CloudVendors</h2>
    <button className='btn btn-primary mb-2' onClick={addNewTodo}>Add CloudVendor</button>
    <div className='container m-auto py-10 px-6'>
          <div className='grid grid-cols-1 md:grid-cols-70/30 w-full gap-6'>
           
    <div className='grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6'>
        {cloudvendor.map((cloudvendor) => (
            <div className='bg-blue rounded-xl shadow-md relative' key={cloudvendor.vendorId}>
                <div className='p-4'>
                    <div className='mb-6' >
                        <div className='text-gray-600 my-2'>{cloudvendor.type}</div>
                        <h3 className='text-xl font-bold'>{cloudvendor.vendorName}</h3>
                    </div>
                    <div className='mb-5'>{cloudvendor.vendorAddress}</div>
                    <h3 className='text-indigo-500 mb-2'>{cloudvendor.vendorPhoneNumber}</h3>
                    <div className='border border-gray-100 mb-5'></div>
                    <div className='flex flex-col lg:flex-row justify-between mb-4'>
                        <div className='text-orange-700 mb-3'>{cloudvendor.vendorId}</div>
                    </div>
                </div>
            </div>
        ))}
    </div>
    </div>
    </div>
</div>

  )
}

export default ListTodoComponent
