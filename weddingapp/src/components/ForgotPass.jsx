import React, { useState ,useEffect} from 'react'; 
import { Link, useNavigate } from 'react-router-dom'; // Import useNavigate here
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const ForgotPass = () => {
    const navigate = useNavigate(); 
    const [email,setEmail]=useState('')
    const[password,setPassword]= useState('')
    const [hide, setHide] = useState({
        name: 'bx bx-show',
        type: 'password'
    });

 
    const handlePass = () => {
        hide.name === 'bx bx-show' ? setHide({ name: 'bx bx-hide', type: 'text' }) : setHide({ name: 'bx bx-show', type: 'password' });
    };

    const handleSubmit =(e)=>{
            e.preventDefault()
            toast.success("Password Changed Sucessfully")
            setTimeout(() => {
                navigate("/")
            }, 1500);

    }
  return (
    <div className=" loginbg flex justify-center items-center h-screen">
            <form onSubmit={handleSubmit} className=" glass bxshadow text-white shadow-md rounded w-[40%] h-[70%] flex flex-col justify-center items-start p-16">
                <h1 className=' self-center pb-4 mb-6 border-b px-5 headingfont text-3xl text-white'>Forget Password</h1>
                <div className="mb-4 w-full">
                    <label className="block text-gray-200 text-lg font-bold mb-2 headingfont" htmlFor="username">
                        Email
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="username"
                        type="text"
                        placeholder=""
                        value={email.toLowerCase()}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="mb-6 w-full">
                    <label className="block text-gray-100 text-lg font-bold mb-2 headingfont" htmlFor="password">
                       Enter New Password
                    </label>
                    <div className=' flex h-fit gap-4  items-center'>
                    <input
                        className="shadow text-lg appearance-none border rounded w-full py-2 px-3 text-gray-700  leading-tight focus:outline-none focus:shadow-outline"
                        id="password"
                        type={hide.type}
                        placeholder=""
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <button type="button" onClick={handlePass} className=' px-4 py-2 rounded-xl bg-blue-500 font-medium text-white'><i className={hide.name}></i></button>
                   
                    </div>
                </div> 
                <div className="flex items-center justify-between w-full">
                    <button 
                        className="bg-blue-500 hover:bg-blue-700 headingfont text-white font-bold w-full py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        type="submit"
                    >
                        Change Password
                    </button>
                    <ToastContainer/>
                </div>
                    
            </form>
        </div>
  )
}

export default ForgotPass