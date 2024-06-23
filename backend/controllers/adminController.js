const bcrypt = require('bcryptjs'); // Asegúrate de importar bcrypt
const Admin = require('../models/admin'); // Importa el modelo de Admin
const User = require('../models/user'); // Importa el modelo de User

// Obtener todos los administradores
const getAdmins = async (req, res) => {
    try {
        const admins = await Admin.find();
        res.json(admins);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener administradores' });
    }
};

// Obtener datos de gráficas
const getGraphs = async (req, res) => {
    try {
        // Este es un ejemplo simple, actualízalo según tu lógica de negocio
        const graphsData = {
            humidity: [],
            temperature: [],
            timestamps: []
        };
        res.json(graphsData);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener datos de gráficas' });
    }
};

// Alertar al usuario basado en datos de sensores
const alertUser = (req, res) => {
    try {
        const alertMessage = {
            user: 'Farmer1',
            alert: 'High humidity detected in your field.'
        };
        res.json(alertMessage);
    } catch (error) {
        res.status(500).json({ message: 'Error al alertar al usuario' });
    }
};

// Crear un nuevo usuario
const addUser = async (req, res) => {
    try {
        const { username, password, role } = req.body;

        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        const newUser = new User({
            username,
            password: hashedPassword,
            role
        });

        await newUser.save(); // Guarda el nuevo usuario en la base de datos

        res.status(201).json(newUser);
    } catch (error) {
        console.error(error); // Para mayor visibilidad en la consola de errores
        res.status(400).json({ message: 'Error al crear el usuario' });
    }
};

// Obtener todos los usuarios
const getUsers = async (req, res) => {
    try {
        const users = await User.find();
        res.json(users);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener usuarios' });
    }
};

// Obtener usuario por ID
const getUserById = async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        if (!user) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json(user);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener el usuario' });
    }
};

// Actualizar un usuario existente
const updateUser = async (req, res) => {
    try {
        const { id } = req.params;
        const { password, ...updateFields } = req.body;

        if (password) {
            const salt = await bcrypt.genSalt(10);
            updateFields.password = await bcrypt.hash(password, salt);
        }

        const updatedUser = await User.findByIdAndUpdate(id, updateFields, { new: true });
        if (!updatedUser) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json(updatedUser);
    } catch (error) {
        res.status(400).json({ message: 'Error al actualizar el usuario' });
    }
};

// Eliminar un usuario
const deleteUser = async (req, res) => {
    try {
        const { id } = req.params;
        const deletedUser = await User.findByIdAndDelete(id);
        if (!deletedUser) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json({ message: 'Usuario eliminado' });
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar el usuario' });
    }
};

module.exports = {
    getAdmins,
    getGraphs,
    alertUser,
    addUser,
    getUsers,
    getUserById,
    updateUser,
    deleteUser
};
