import React, { useState } from 'react'; 
import { Link  ,useNavigate} from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Signup = ({isLogin}) => {
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [hide, setHide] = useState({
        name: 'bx bx-show',
        type: 'password'
    });
    const navigate = useNavigate()

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email,
                    name,
                    password
                })
            });

            if (response.ok) {
                // Registration successful, handle success
                 toast.success('Registered successfully please Log in');
                 setTimeout(() => {
                    navigate("/login")
                 }, 1000);
                
                
            } else {
                // Registration failed, handle error
                toast.error('User alredy exist please log in');
            }
        } catch (error) {
            console.error('Error registering user:', error);
            toast.error('Error registering user');
        }
    };

    const handlePass = () => {
        setHide(prevState => ({
            name: prevState.name === 'bx bx-show' ? 'bx bx-hide' : 'bx bx-show',
            type: prevState.type === 'password' ? 'text' : 'password'
        }));
    };

    return (
        <div className="loginbg flex justify-center items-center h-fit">
            
            <form onSubmit={handleSubmit} className="glass bxshadow text-white shadow-md rounded w-[40%] h-[80vh] my-[12vh] flex flex-col justify-center items-start p-16">
            <ToastContainer/>
                <h1 className='self-center pb-4 mb-6 border-b px-5 headingfont text-3xl text-white'>Create an Account</h1>
                <div className="mb-4 w-full">
                    <label className="block text-gray-200 text-lg font-medium mb-2" htmlFor="name">
                        Name
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="name"
                        type="text"
                        placeholder=""
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>
                <div className="mb-4 w-full">
                    <label className="block text-gray-200 text-lg font-medium mb-2" htmlFor="username">
                        Email
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="username"
                        type="text"
                        placeholder=""
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="mb-6 w-full">
                    <label className="block text-gray-100 text-lg font-medium mb-2" htmlFor="password">
                        Password
                    </label>
                    <div className='flex h-fit gap-4 items-center'>
                        <input
                            className="shadow text-lg appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="password"
                            type={hide.type}
                            placeholder=""
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <button type='button' onClick={handlePass} className='px-4 py-2 rounded-xl bg-blue-500 font-medium text-white'><i className={hide.name}></i></button>
                    </div>
                </div>
                <div className="flex items-center justify-between w-full">
                    <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-medium w-full py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        type="submit"
                    >
                        Submit
                    </button>
                </div>
                <div className='flex justify-between w-full mt-4'><p>Have an account ?</p> <Link className='text-blue-700 border-b px-1 border-blue-700' to='/login'>Login</Link></div>
            </form>
        </div>
    );
};

export default Signup;
