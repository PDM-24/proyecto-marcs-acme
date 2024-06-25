// controllers/commentController.js
const Comment = require('../models/comment');

// Obtener todos los comentarios del usuario
const getComments = async (req, res) => {
    try {
        const userId = req.user.id;
        const comments = await Comment.find({ user: userId });
        res.json(comments);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener comentarios' });
    }
};

// Crear un nuevo comentario
const addComment = async (req, res) => {
    try {
        const newComment = new Comment({
            comment: req.body.comment,
            location: req.body.location,
            cropType: req.body.cropType,
            soilType: req.body.soilType,
            observationDateTime: req.body.observationDateTime,
            user: req.user.id // Asociar el comentario con el usuario autenticado
        });

        await newComment.save();
        res.status(201).json(newComment);
    } catch (error) {
        res.status(400).json({ message: 'Error al crear el comentario' });
    }
};

// Eliminar un comentario
const deleteComment = async (req, res) => {
    try {
        const commentId = req.params.id;
        const comment = await Comment.findOneAndDelete({ _id: commentId, user: req.user.id });

        if (!comment) {
            return res.status(404).json({ message: 'Comentario no encontrado' });
        }

        res.json({ message: 'Comentario eliminado' });
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar el comentario' });
    }
};

module.exports = {
    getComments,
    addComment,
    deleteComment
};
